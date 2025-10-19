# Blogistan

A clean and minimal blogging platform where users can register, create posts, comment, and search through content in real-time. Includes both **dark** and **light mode**, a modern responsive UI, and is fully containerized with **Docker**.

## ✨ Features

- 📝 User registration & login
- 📚 Create, edit, delete your own blog posts
- 💬 Comment on any blog (including other users' posts)
- 🌗 Toggle between **Dark Mode** and **Light Mode**
- 🔍 Full-text search with **Elasticsearch**
    - Real-time dynamic search results as you type
- 🧑 Account deletion option
- ⚡ Dynamic, reactive UI — simple and smooth

## 🚀 Tech Stack

- Backend: [Spring Boot]
- Frontend: [HTML/CSS, JS]
- Database: [MYSQL, Elasticsearch]
- Search Engine: **Elasticsearch**
- Containerization: **Docker + Docker Compose**

## 🐳 Getting Started with Docker

To run the entire application (frontend, backend, database, and Elasticsearch), all you need is **Docker** and **Docker Compose** installed.

> ### 🖥️ Windows & macOS Users
> You need to install **Docker Desktop** to run Docker on your system.  
> 👉 Download it here: [https://www.docker.com/products/docker-desktop/](https://www.docker.com/products/docker-desktop/)

> ### 🐧 Linux(Ubuntu) Users
> Install Docker Engine & Compose based on your distribution.  
> Official guide: [https://docs.docker.com/engine/install/ubuntu/](https://docs.docker.com/engine/install/)

## 🚀 Installation

### 1. Clone the repository

```bash
git clone https://github.com/EnesUluc/ElasticsearchWithSpringBoot.git
cd ElasticsearchWithSpringBoot

### 🔧 1. Run the app
```bash
docker compose up -d --build

🌐 Access the App
Once the containers are running, access the application at:
http://localhost:8080

🔌 Port Mapping
Service      |  	Port (Host → Container)
-------------------------------------------
Application	        8080 → [internal]
MySQL Database	    3307 → 3306
Elasticsearch	    9201 → 9200

🔍 Search Functionality

Blogify integrates Elasticsearch for full-text search.
You can type any word from a blog’s title or content, and matching results will appear in real-time.


🛠️ Future Improvements

* User profile pages

* Rich text or Markdown editor

* Blog post tags or categories

* Image upload support

* Bookmark / Like system

* Admin panel

* Notifications

📄 License
This project is licensed under the MIT License.

Made with ❤️ by Muhammed Enes Uluç