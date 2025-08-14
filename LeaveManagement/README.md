# Leave Management System MVP

## Overview

This Leave Management System is designed to automate and simplify leave management processes for a startup company with 50 employees. It provides functionalities for HR to manage employee records, approve or reject leave requests, and employees to apply for leave and monitor their leave balance. The system uses a secure Spring Boot backend with JWT authentication and a MySQL database.

## Features

### HR Features
- Add new employees with detailed profiles
- Remove employees and their user credentials
- Approve or reject leave requests submitted by employees
- View all leave requests or filter by pending status

### Employee Features
- Secure login using JWT authentication
- Apply for leave without specifying employee ID (derived from JWT)
- View current leave balance

## Architecture

### Frontend
- (Planned) React or Angular SPA for user interaction
- Communicates with the backend over HTTPS REST APIs
- Handles login, leave application, employee and HR dashboards

### Backend
- Spring Boot REST API application
- JWT-based authentication and role-based authorization using Spring Security
- Layered architecture: Controller → Service → Repository
- Core components:
    - Authentication module managing login and token validation
    - Employee service handling profile and leave application logic
    - HR service for employee management and leave approvals

### Database
- MySQL relational database
- Key tables: `users`, `employee`, `leaves`
- Relationships:
    - One-to-one between `employee` and `users`
    - One-to-many between `employee` and `leaves`

### Security
- JWT tokens authorize API access, embedding user roles (`ROLE_EMPLOYEE`, `ROLE_HR`)
- Spring Security filters and strict HTTP firewall enforce secure and valid requests
- Role-based endpoint access control (e.g., HR-only endpoints)

## API Endpoints

### Authentication
- `POST /auth/login` – Authenticate user and return JWT token

### HR APIs
- `POST /hr/addUser` – Add new employee and user
- `DELETE /hr/removeUser/{employeeId}` – Remove employee and user
- `PUT /hr/approveLeave/{leaveId}` – Approve leave request
- `PUT /hr/rejectLeave/{leaveId}` – Reject leave request
- `GET /hr/allLeaves` – Fetch all leave requests
- `GET /hr/pendingLeaves` – Fetch leave requests pending approval

### Employee APIs
- `POST /employee/applyLeave` – Apply for leave (employee inferred from JWT)
- `GET /employee/leaveBalance` – Get employee leave balance

## Data Validation and Business Rules
- End date of leave must not precede start date
- Leave cannot be applied for before the employee’s joining date
- Requested leave days must not exceed the employee's leave balance
- Overlapping leave requests for the same employee are disallowed
- Leave balance is deducted only upon HR approval, not at application time

## How to Run

- Set up MySQL database and update application properties with the database credentials
- Build the project with Maven
- Run the Spring Boot application on your preferred port (default 8080)
- Use API clients like Postman to authenticate, add employees, apply leaves, and approve/reject requests
- Include JWT token in Authorization headers for protected endpoints

## Known Issues and Future Enhancements

- Currently, raw entity objects are returned by APIs; creating dedicated Response DTOs will improve security and response clarity
- Implement frontend SPA for better user experience
- Add unit and integration tests to improve code reliability
- Provide additional HR features such as employee data editing and leave reports
- Introduce caching, pagination, and filtering in leave history APIs
- Scale application architecture for larger organizations and higher load

## Contact

For further assistance or inquiries, please contact:

- Name: R Srikrishna Kashyap
- Email: rayabagikashyap@gmail.com
- Phone: +91 8247863082

***

Thank you for reviewing this Leave Management System MVP documentation!