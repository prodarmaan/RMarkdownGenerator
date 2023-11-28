import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RMarkdownGenerator {

    // Declare the scanner object as a class variable
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Your Name: ");
        String name = scanner.nextLine();

        System.out.print("Homework Assignment Number: ");
        int assignmentNumber = Integer.parseInt(scanner.nextLine());

        System.out.print("Number of Questions: ");
        int numQuestions = Integer.parseInt(scanner.nextLine());

        System.out.print("Output File Name: ");
        String outputFileName = scanner.nextLine();

        System.out.print("Output File Path: ");
        String outputPath = scanner.nextLine();

        generateRMarkdown(name, assignmentNumber, numQuestions, outputFileName, outputPath);
    }

    private static void generateRMarkdown(String name, int assignmentNumber, int numQuestions,
                                          String outputFileName, String outputPath) {
        // Create the RMarkdown content using the provided inputs
        String rMarkdownContent = "---\n" +
                "title: \"Homework Assignment " + assignmentNumber + "\"\n" +
                "author: \"" + name + "\"\n" +
                "date: \"`r Sys.Date()`\"\n" +
                "output:\n" +
                "  pdf_document: default\n" +
                "  word_document: default\n" +
                "  html_document:\n" +
                "    df_print: paged\n" +
                "---\n" +
                "\n" +
                "```{r setup, include=FALSE}\n" +
                "knitr::opts_chunk$set(echo = TRUE)\n" +
                "library(tidyverse)\n" +
                "## Include the command to load the data here!!!!\n" +
                "```\n" +
                "\n" +
                "* * *\n";

        // Prompt for exercise and page numbers for each question
        for (int i = 1; i <= numQuestions; i++) {
            System.out.print("Exercise Number for Question " + i + ": ");
            double exerciseNumber = Double.parseDouble(scanner.nextLine());

            System.out.print("Page Number for Question " + i + ": ");
            int pageNumber = Integer.parseInt(scanner.nextLine());

            // Append questions based on the provided exercise and page numbers
            rMarkdownContent += "## Question " + i + " (Exercise " + exerciseNumber + " on Page " + pageNumber + "):  \n";
        }

        // Save the content to the specified output file with .Rmd extension
        saveToFile(outputPath + "/" + outputFileName + ".Rmd", rMarkdownContent);
    }

    private static void saveToFile(String outputPath, String content) {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(content);
            System.out.println("R Markdown content saved to: " + outputPath);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
