# Hospital Management System

A Java-based application developed to manage basic hospital operations using MySQL and JDBC. It also includes an AI-powered chatbot using Ollama and Llama.

## Features

- Add and view patients
- Add doctors and their specializations
- Schedule patient appointments
- Generate patient bills
- AI chatbot for general hospital-related questions

## Technologies Used

- Java
- JDBC
- MySQL
- Ollama
- Llama 3.2 1B
- JSON-java library

## Requirements

- JDK 11 or later
- MySQL Server
- MySQL Connector/J
- JSON-java library
- Ollama

## Setup

1. Clone this repository.
2. Create a MySQL database named hospital.
3. Create the required tables: patient, doctor, appointment, and billing.
4. Configure your MySQL password using the `DB_PASSWORD` environment variable.
5. Add MySQL Connector/J and JSON-java JAR files to the project build path.
6. Install Ollama and download the AI model:

```bash
ollama pull llama3.2:1b
```

7. Run HospitalManagementSystem.java from Eclipse.

## AI Chatbot

The chatbot uses the locally running Llama 3.2 1B model through Ollama to answer general questions about hospital services, appointments, doctors, and billing.
