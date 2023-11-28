function generateRMarkdown() {
  var name = document.getElementById('name').value;
  var assignmentNumber = document.getElementById('assignmentNumber').value;
  var numQuestions = document.getElementById('numQuestions').value;
  var outputFileName = document.getElementById('outputFileName').value;

  var rMarkdownContent = "---\n" +
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
                        "* * *\n" +
                        "\n";

  // Add questions based on the provided exercise and page numbers
  for (var i = 1; i <= numQuestions; i++) {
      var exerciseNumber = document.getElementById('exerciseNumber' + i).value;
      var pageNumber = document.getElementById('pageNumber' + i).value;

      // Append questions based on the provided exercise and page numbers
      rMarkdownContent += "## Question " + i + " (Exercise " + exerciseNumber + " on Page " + pageNumber + "):  \n";
  }

  // Create a Blob with the RMarkdown content
  var blob = new Blob([rMarkdownContent], { type: 'text/markdown' });

  // Create a link element and trigger a download
  var link = document.createElement('a');
  link.href = window.URL.createObjectURL(blob);
  link.download = outputFileName + ".Rmd";
  link.click();
}

// Dynamically add input fields for exercise and page numbers based on the number of questions
document.getElementById('numQuestions').addEventListener('change', function () {
  var numQuestions = parseInt(this.value);
  var questionsDiv = document.getElementById('questions');
  questionsDiv.innerHTML = "";  // Clear previous questions

  for (var i = 1; i <= numQuestions; i++) {
      var exerciseInput = document.createElement('input');
      exerciseInput.type = 'text';
      exerciseInput.placeholder = i + '. Exercise Number';
      exerciseInput.id = 'exerciseNumber' + i;
      questionsDiv.appendChild(exerciseInput);

      var pageInput = document.createElement('input');
      pageInput.type = 'text';
      pageInput.placeholder = i + '. Page Number';
      pageInput.id = 'pageNumber' + i;
      questionsDiv.appendChild(pageInput);

      questionsDiv.appendChild(document.createElement('br'));
  }
});