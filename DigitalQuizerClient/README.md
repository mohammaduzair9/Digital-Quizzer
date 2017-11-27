# Digital-Quiz-Maker

## Description
This is a Java based desktop application that provides an interactive interface to an instructor for generating a quiz.

## Functionalities for Instructors:
Instructors after logging in, are allowed to create a Quiz by providing a short title and description for the quiz. Once a quiz has been created, the application then allows them to add a question (of type multiple choice, true false or numeric). All questions are indexed with their position editable by the instructor but not their content. For each question the instructor must add a text for the question, options (4 for MCQs, 2 for True False and none for numeric), expected correct answer, and a maximum achievable score.

## Functionalities for Students:
Students can view all quizzes and take a particular quiz. The student attempts the quiz by providing his answers, which are matched against the correct answers and a score is calculated. At the end of the quiz, the score of student is displayed in this attempt.

## Code Management:
  * MVC pattern
  * Maven
  * Unit Tests.
