# DigitalQuizzer

In order to solve the problem of generating digital quizzes, I have designed an application that provides an interactive interface (Desktop and Mobile) to an instructor for generating quizes, and student to attempt those quizes.

## Functionality of Users

Instructors after logging in, are allowed to create a Quiz by providing a short title and description for the quiz. Once a quiz has been created, it allows them to add questions (of type multiple choice, true false or numeric). 
Students can view all quizzes and take any quiz. At the end of the quiz, their score is generated for that attempt.

## Server Side

The server provides a rest api by using Spring framework.

## Client Side

There are 2 client applications, one for Desktop (Java) and other for Mobile (Android).
