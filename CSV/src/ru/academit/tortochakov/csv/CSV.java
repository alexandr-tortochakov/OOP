package ru.academit.tortochakov.csv;

import java.io.*;
import java.util.ArrayList;

public class CSV {
    public static void main(String[] args) {
        try (Reader reader = new InputStreamReader(new FileInputStream("D:/input.csv"));
             PrintWriter writer = new PrintWriter("D:/output.html")) {
            ArrayList<ArrayList<String>> table = new ArrayList<>();
            String cell = "";
            ArrayList<String> row = new ArrayList<>();
            int r;
            int quotesNumber = 0;
            while ((r = reader.read()) != -1) {
                char ch = (char) r;
                if (ch == '\r') {
                    continue;
                }
                if (ch == '"') {
                    quotesNumber++;
                }
                if (ch != ',' && ch != '\n') {
                    cell += ch;
                } else if (ch == ',' && quotesNumber % 2 != 0) {
                    cell += ch;
                } else if (ch == ',' && quotesNumber % 2 == 0) {
                    if (quotesNumber > 0) {
                        cell = cell.substring(1, cell.length() - 1);
                    }
                    row.add(cell);
                    cell = "";
                    quotesNumber = 0;
                } else if (ch == '\n' && quotesNumber % 2 != 0) {
                    cell += ch;
                } else if (ch == '\n' && quotesNumber % 2 == 0) {
                    if (quotesNumber > 0) {
                        cell = cell.substring(1, cell.length() - 1);
                    }
                    row.add(cell);
                    cell = "";
                    table.add((ArrayList<String>) row.clone());
                    row.clear();
                    quotesNumber = 0;
                }
            }
            if (!cell.equals("")) {
                if (quotesNumber > 0) {
                    cell = cell.substring(1, cell.length() - 1);
                }
                row.add(cell);
                table.add((ArrayList<String>) row.clone());
            }

            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>");
            writer.println("TITLE");
            writer.println("</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border>");
            for (ArrayList<String> aTable : table) {
                writer.println("<tr>");
                row = aTable;
                for (String aRow : row) {
                    cell = aRow;
                    cell = cell.replaceAll("\n", "<br/>").replaceAll("\"\"", "\"");
                    writer.println("<td>" + cell + "</td>");
                }
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Непонятно че произошло");
        }
    }
}