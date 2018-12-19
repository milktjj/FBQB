package com.iecas.oceanologybigdata.util;


import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FuBiao {


    public static List<Object[]> test(String execlFilePath, String target) throws IOException {
        // 根据参数传入的数据文件路径和文件名称，组合出excel 数据文件的绝对路径
        // 声明一个file 文件对象
        File file = new File(execlFilePath);

        // 创建FileInputStream 对象用于读取excel 文件
        FileInputStream inputStream = new FileInputStream(file);


        // 声明Workbook 对象
        Workbook wb = null;

        // 获取文件名参数的后缀名，判断xlsx文件还是xls文件
        String fileExtensionName = execlFilePath.substring(execlFilePath.lastIndexOf("."));

        // 判断文件类型如果是xlsx，则使用XSSFWorkbook 对象进行实例化
        // 判断文件类型如果是xls，则使用HSSFWorkbook 对象进行实例化
        if (fileExtensionName.equals(".xlsx")) {
            //如果是2007的，也就是.xlsx， 让Workbook = new XSSFWorkbook(inputStream);
            wb = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            //如果是2003的，也就是.xls， 让Workbook = new HSSFWorkbook(inputStream);
            wb = new HSSFWorkbook(inputStream);
        }

        // 通过sheetName参数，生成sheet 对象
        DataFormatter formatter = new DataFormatter();
        //Sheet Sheet1 = wb.getSheet(sheetName);
        Sheet sheet1 = wb.getSheetAt(0);
        List<Object[]> records = new ArrayList<Object[]>();
        int i = 0, j = 0;
        int length = sheet1.getRow(0).getLastCellNum();
        System.out.println(wb.getNumberOfSheets());
        for (Sheet sheet : wb) {
            boolean isFirst = true;
            //System.out.println(sheet.getSheetName() + " " + sheet.getLastRowNum());
            while(sheet.getRow(i) != null) {
                j = 0;
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                Row row = sheet.getRow(i);
                Object[] objects = new Object[length + 1];
                //objects[length] = "0";
                objects[length] = sheet.getSheetName();
                //objects[length + 2] = sailName;
                while(row.getCell(j) != null) {
                    if (j == length)
                        break;
                    Cell cell = row.getCell(j);
                    //CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                    //System.out.print(cellRef.formatAsString());
                    //System.out.print(" - ");

                    // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
                    //System.out.println(text);

                    // Alternatively, get the value and format it yourself
                    switch (cell.getCellType()) {
                        case STRING:
                            if (cell.getRichStringCellValue().getString().equals("表"))
                                objects[j++] = 0;
                            else if (cell.getRichStringCellValue().getString().equals("底"))
                                objects[j++] = objects[j - 2];
                            else if (isValid(cell.getRichStringCellValue().getString()))
                                objects[j++] = cell.getRichStringCellValue().getString();
                            else
                                objects[j++] = -9999;
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                short format = cell.getCellStyle().getDataFormat();
                                System.out.println(format);
                                System.out.println(new SimpleDateFormat("HH:mm").format(cell.getNumericCellValue()));
                                SimpleDateFormat sdf = null;
                                if ((176 <= format && format < 178) || (182 <= format && format <= 196)
                                        || (210 <= format && format <= 213) || (208 == format)) { // 日期
                                    sdf = new SimpleDateFormat("MM/dd");
                                } else if (format == 20 || format == 179|| format == 178|| format == 180 || format == 32 || (200 <= format && format <= 209)) { // 时间
                                    sdf = new SimpleDateFormat("HH:mm");
                                }
                                //System.out.println(cell.getCellStyle().getDataFormat());
                                double value = cell.getNumericCellValue();
                                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                                objects[j++] = sdf.format(date);
                                //objects[j++] = cell.getNumericCellValue();
                            } else {
                                objects[j++] = cell.getNumericCellValue();
                            }
                            break;
                        case BOOLEAN:
                            objects[j++] = cell.getBooleanCellValue();
                            break;
                        case FORMULA:
                            objects[j++] = cell.getCellFormula();
                            break;
                        case BLANK:
                            objects[j] = records.get(i - 1)[j];
                            //objects[j] = "null";
                            ++j;
                            break;
                        default:
                            objects[j] = records.get(i - 1)[j];
                            ++j;
                    }


                }

                records.add(objects);
                ++i;
            }
            resultToFile(records, target, heads, "ttt");
            records.clear();
            i=0;
            //System.out.println("ddd");
            //System.out.println(records.size());
        }
        //System.out.println(records.size());
        return records;

    }

    public static boolean isValid(String str) {
        if (str.equals("未检出") || str.equals("数据异常"))
            return false;
        return true;
    }


    public static void resultToFile(List<Object[]> result, String path, String heads, String sheetName) throws IOException {

        File file = new File(path + "\\" + sheetName + ".csv");
        // if file doesnt exists, then create it
        boolean isExist = file.exists();
        if (!isExist) {
            file.createNewFile();

        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
        BufferedWriter bw = new BufferedWriter(fw);
        if (!isExist) {
            bw.write(heads);
            bw.newLine();
        }
        //System.out.println(result.size());
        for (int i = 1; i < result.size(); ++i) {
            for (int j = 0; j < result.get(i).length; ++j) {
                boolean isValid = true;
                for (int k = 0; k < result.get(i).length;++k) {
                    if(result.get(i)[k] == null) {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {

                    //bw.write("null");
                    break;
                } else {
                    if (j == 1 && j == 2) {

                        Expression e = new ExpressionBuilder(result.get(i)[j].toString()).build();
                        DecimalFormat df = new DecimalFormat("#.00");
                        result.get(i)[j] = df.format(e.evaluate());

                    }
                    //System.out.println(result.get(i)[j]);
                    //System.out.println(i + " " + j);

                    //bw.write(content.toString());
                    Object content = result.get(i)[j];
                    bw.write(content.toString());

                }
                if (j != result.get(i).length - 1)
                    bw.write(",");
                //System.out.print(",");

            }
            bw.newLine();


        }

        bw.close();

        System.out.println("Done");


    }


    public static String sailName = "2014年5月科学三号黄海春季调查航次";
    //public static String heads = "site,lon,lat,date,time,depthOfWater_M,layerOfWater_M,PO4_μmol/L,NO3_μmol/L,SiO3_μmol/L,sailId,sailName";
    public static String heads = "site,lon,lat,name";


    public static void main(String[] args) throws IOException {

        //String filePath = "C:\\Users\\MILK\\Desktop\\营养盐\\2014年5月科学三号黄海春季调查航次\\五项营养盐\\" + "营养盐上交数据-1.xlsx";
        String filePath = "D:\\南海所航次.xlsx";
        String sheetName = "D:";
        String target = "C:\\Users\\MILK\\Desktop\\output\\";
        List<Object[]> result = test(filePath, target);
        //String heads = new String();
        //result.
        //resultToFile(result, target, heads, "ttt");






    }


}
