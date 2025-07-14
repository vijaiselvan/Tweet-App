# 🐦 Tweet App – Full Stack Microblogging Platform

![React](https://img.shields.io/badge/frontend-React-blue?logo=react)
![Java Spring Boot](https://img.shields.io/badge/backend-Spring%20Boot-brightgreen?logo=springboot)
![MongoDB](https://img.shields.io/badge/database-MongoDB-green?logo=mongodb)
![JWT](https://img.shields.io/badge/authentication-JWT-orange?logo=jsonwebtokens)
![AWS](https://img.shields.io/badge/deployment-AWS-yellow?logo=amazonaws)
![JUnit](https://img.shields.io/badge/tested%20with-JUnit-red)

---

## 📘 Overview

**Tweet App** is a secure, cloud-deployed microblogging platform built as part of the **Cognizant Digital Honors Program - Full Stack Engineering (FSE1 Certified)**.  
It allows users to register, log in, post/edit/delete tweets, like/reply to posts, and search/view user profiles - backed by a RESTful API and deployed on AWS.

---

## 🛠️ Features

- 🔐 **JWT Authentication**: Secures endpoints with Spring Security and JWT tokens.
- 📝 **Tweet CRUD**: Users can post, edit, delete tweets, and view a global tweet feed.
- 💬 **Replies & Likes**: Engage with other tweets through comments and likes.
- 👥 **User Search**: Find and view registered users and their tweets.
- ☁️ **Cloud Deployment**: Hosted backend on **AWS Elastic Beanstalk**, MongoDB on **AWS EC2**, and managed EC2 via **PuTTY**.
- 🧪 **Testing**: Used **JUnit** for backend testing; Git for version control.

---

## 🔗 Preview Screenshots

| Feature                  | Screenshot |
|--------------------------|------------|
| 🔐 Login Page            | ![Login](assets/Login_Page.png) |
| 🆕 User Registration     | ![Register](assets/Registation.png) |
| 🏠 Tweet Feed (Home)     | ![Home](assets/Home.png) |
| 👤 Profile with Tweets   | ![Profile](assets/Profile.png) |
| 👥 All Users             | ![Users](assets/See All Users.png) |
| 🔍 Search User           | ![Search](assets/Search_User.png) |
| 📄 User Schema           | ![User Schema](assets/user_schema.png) |
| 📄 Tweet Schema          | ![Tweet Schema](assets/tweet_schema.png) |


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
```
### 🐦 Tweet Collection

```json
{
  "tweetTag": "#PositiveVibes #Motivation",
  "tweet": "🌟 Every day is a fresh start. Take a deep breath...",
  "username": "vijay",
  "createdDate": "2025-07-14T11:19:02.674Z",
  "like": 0,
  "tweetReply": []
}
```

## 🌐 Deployment Architecture

- ⚙️ **Backend**: Java Spring Boot APIs hosted on **AWS Elastic Beanstalk**
- 🗃️ **Database**: MongoDB hosted on **AWS EC2**, accessed via **PuTTY SSH**
- 🖼️ **Frontend**: ReactJS served locally *(or can be deployed via Netlify/EC2)*
- 📁 **Static Assets**: Stored in **AWS S3** 
- 🔧 **Tools Used**: IntelliJ, VS Code, Git, Postman, PuTTY, MongoDB Compass

## 🚀 Run Locally
Requirements: Java 17+, Node.js, MongoDB, Maven

# Clone the repository
```bash
git clone https://github.com/vijaiselvan/Tweet-App.git
```

# Backend Setup
```bash
cd backend
mvn clean install
java -jar target/tweet-app.jar
```

# Frontend Setup
```bash
cd ../frontend
npm install
npm start
```

## ✅ Skills Demonstrated

- 🔧 **Full-stack development** with Spring Boot and React
- 🔐 **Authentication** using JWT and Spring Security
- ☁️ **Cloud deployment** on AWS EC2 and Elastic Beanstalk
- 🧩 **NoSQL data modeling** and queries using MongoDB
- 🔁 **REST API design** and frontend-backend integration
- 🧪 **Unit testing** with JUnit for backend validation
- 🔄 **Git-based collaboration** and adherence to full **SDLC process**

## 📄 Certificate

**Cognizant Digital Honors Program – Full Stack Engineering (FSE1)**  
🟢 *Certified Developer (Jul 2022 – Sept 2022)*  

---

## 👨‍💻 Contact

**Vijai Selvan**  
📧 vijaiselvanvj@gmail.com  
🌐 [LinkedIn](https://www.linkedin.com/in/vijai-selvan/)
📂 [Portfolio](https://vijaiselvan.github.io/portfolio/)



