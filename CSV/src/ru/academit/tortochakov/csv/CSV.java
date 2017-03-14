package ru.academit.tortochakov.csv;

import java.io.*;
import java.util.ArrayList;

public class CSV {
    public static void main(String[] args) {
        if (args.length != 2) {
            help();
            return;
        }
        ArrayList<ArrayList<String>> table = parseCSV(args[0]);
        if (table == null) {
            return;
        }
        writeHTML(table, args[1]);
    }

    public static ArrayList<ArrayList<String>> parseCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            ArrayList<ArrayList<String>> table = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            ArrayList<String> row = new ArrayList<>();
            int r;
            int quotesNumber = 0;
            while ((r = br.read()) != -1) {
                char ch = (char) r;
                if (ch == '\r') {
                    continue;
                }
                if (ch == '"') {
                    quotesNumber++;
                }
                boolean isEven = quotesNumber % 2 == 0;
                if (ch != ',' && ch != '\n' && ch != '>' && ch != '<' && ch != '&') {
                    builder.append(ch);
                } else if (ch == ',') {
                    if (isEven) {
                        if (quotesNumber > 0) {
                            row.add(builder.substring(1, builder.length() - 1));
                        } else {
                            row.add(builder.toString());
                        }
                        builder.setLength(0);
                        quotesNumber = 0;
                    } else {
                        builder.append(ch);
                    }
                } else if (ch == '\n'){
                    if (isEven) {
                        if (quotesNumber > 0) {
                            row.add(builder.substring(1, builder.length() - 1));
                        } else {
                            row.add(builder.toString());
                        }
                        builder.setLength(0);
                        table.add(row);
                        row = new ArrayList<>();
                        quotesNumber = 0;
                    } else {
                        builder.append(ch);

                    }
                } else if (ch == '>') {
                    builder.append("&gt");
                } else if (ch == '<') {
                    builder.append("&lt");
                } else {
                    builder.append("&amp");
                }
            }
            if (!builder.toString().equals("")) {
                if (quotesNumber > 0) {
                    row.add(builder.substring(1, builder.length() - 1));
                } else {
                    row.add(builder.toString());
                }
                table.add(row);
                builder.setLength(0);
            }
            return table;
        } catch (IOException e) {
            System.out.println("Файл не найден или чтение невозможно");
            return null;
        }
    }

    public static void writeHTML(ArrayList<ArrayList<String>> table, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            StringBuilder builder = new StringBuilder();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>");
            writer.println("TITLE");
            writer.println("</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border>");
            for (ArrayList<String> row : table) {
                writer.println("<tr>");
                for (String aCell : row) {
                    builder.append("<td>")
                            .append(aCell.replaceAll("\n", "<br/>")
                                    .replaceAll("\"\"", "\""))
                            .append("</td>");
                    writer.println(builder.toString());
                    builder.setLength(0);
                }
                writer.println("</tr>");
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (IOException e) {
            System.out.println("Невозможно записать в файл");
        }
    }

    public static void help() {
        System.out.println("Usage:\n\tCSV <input filename> <output filename>");
    }
}