# 🐦 Tweet App – Full Stack Microblogging Platform

![React](https://img.shields.io/badge/frontend-React-blue?logo=react)
![Java Spring Boot](https://img.shields.io/badge/backend-Spring%20Boot-brightgreen?logo=springboot)
![MongoDB](https://img.shields.io/badge/database-MongoDB-green?logo=mongodb)
![JWT](https://img.shields.io/badge/authentication-JWT-orange?logo=jsonwebtokens)
![AWS](https://img.shields.io/badge/deployment-AWS-yellow?logo=amazonaws)
![JUnit](https://img.shields.io/badge/tested%20with-JUnit-red)

---

## 📘 Overview

**Tweet App** is a secure, cloud-deployed microblogging platform built as part of the **Cognizant Digital Honors Program – Full Stack Engineering (FSE1 Certified)**.  
It allows users to register, log in, post/edit/delete tweets, like/reply to posts, and search/view user profiles — backed by a RESTful API and deployed on AWS.

---

## 🛠️ Features

- 🔐 **JWT Authentication**: Secures endpoints with Spring Security and JWT tokens.
- 📝 **Tweet CRUD**: Users can post, edit, delete tweets, and view a global tweet feed.
- 💬 **Replies & Likes**: Engage with other tweets through comments and likes.
- 👥 **User Search**: Find and view registered users and their tweets.
- ☁️ **Cloud Deployment**: Hosted backend on **AWS Elastic Beanstalk**, MongoDB on **AWS EC2**, and managed EC2 via **PuTTY**.
- 🧪 **Testing**: Used **JUnit** for backend testing; Git for version control.

---

## 🔗 Screenshots

| Feature                  | Screenshot |
|--------------------------|------------|
| 🔐 Login Page            | ![Login](assets/Login_Page.png) |
| 🆕 User Registration     | ![Register](assets/Registation.png) |
| 🏠 Tweet Feed (Home)     | ![Home](assets/Home.png) |
| 👤 Profile with Tweets   | ![Profile](assets/Profile.png) |
| 👥 All Users             | ![Users](assets/See_All_Users.png) |
| 🔍 Search User           | ![Search](assets/Search_User.png) |
| 📄 User Schema           | ![User Schema](assets/user_schema.png) |
| 📄 Tweet Schema          | ![Tweet Schema](assets/tweet_schema.png) |

> Replace `assets/...` with the actual paths or GitHub URLs of your uploaded screenshots.

---

## 🧩 MongoDB Document Samples

### 🧑‍💼 User Collection

```json
{
  "loginId": "vijai45",
  "firstName": "Vijai",
  "lastName": "Selvan",
  "emailId": "vijayselvanvj@gmail.com",
  "username": "vijay",
  "contactNumber": "8825411203"
}

### 🐦 Tweet Collection

{
  "tweetTag": "#PositiveVibes #Motivation",
  "tweet": "🌟 Every day is a fresh start. Take a deep breath...",
  "username": "vijay",
  "createdDate": "2025-07-14T11:19:02.674Z",
  "like": 0,
  "tweetReply": []
}

