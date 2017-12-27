# DigitalQuizzer

In order to solve the problem of generating digital quizzes, this is a server/client application that provides an interactive interface (Desktop and Mobile) to an instructor for generating quizes, and student to attempt those quizes.

## Functionalities for Instructors:
Instructors after logging in, are allowed to create a Quiz by providing a short title and description for the quiz. Once a quiz has been created, the application then allows them to add a question (of type multiple choice, true false or numeric). For each question the instructor must add a text for the question, options (4 for MCQs, 2 for True False and none for numeric), expected correct answer, and a maximum achievable score.

## Functionalities for Students:
Students can view all quizzes and take a particular quiz. The student attempts the quiz by providing his answers, which are matched against the correct answers and a score is calculated. At the end of the quiz, the score of student is displayed in this attempt.

## Server Side

The server provides a rest api by using Spring framework.

## Client Side

There are 2 client applications, one for Desktop (Java) and other for Mobile (Android).
