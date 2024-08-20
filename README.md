# Front-Office-Team-Portal

---------------------------------------------------
Table Required for the Project
---------------------------------------------------

# Database Schema

## Admin_Details
| Column Name  | Data Type | Constraints        | Notes              |
|--------------|-----------|-------------------|--------------------|
| USER_ID      | INTEGER   | PRIMARY KEY (PK)   | AUTO_INCREMENT     |
| NAME         | VARCHAR   |                   |                    |
| EMAIL        | VARCHAR   | UNIQUE             |                    |
| PHNO         | VARCHAR   |                   |                    |
| PHNO         | INTEGER   | UNIQUE             |                    |
| PWD          | VARCHAR   |                   |                    |
| ACC_STATUS   | VARCHAR   |                   | DEFAULT: 'LOCKED'  |

## Student_Enquiries
| Column Name      | Data Type | Constraints        | Notes              |
|------------------|-----------|-------------------|--------------------|
| ENQUIRY_ID       | INTEGER   | PRIMARY KEY (PK)   | AUTO_INCREMENT     |
| STUDENT_NAME     | VARCHAR   |                   |                    |
| PHNO             | INTEGER   |                   |                    |
| CLASS_MODE       | VARCHAR   |                   |                    |
| COURSE_NAME      | VARCHAR   |                   |                    |
| ENQUIRY_STATUS   | VARCHAR   |                   | DEFAULT: 'NEW'     |
| CREATED_DATE     | DATE      |                   |                    |
| UPDATED_DATE     | DATE      |                   |                    |
| USER_ID          | INTEGER   | FOREIGN KEY (FK)  | REF: `Admin_Details` |

## Courses
| Column Name  | Data Type | Constraints        | Notes              |
|--------------|-----------|-------------------|--------------------|
| COURSE_ID    | INTEGER   | PRIMARY KEY (PK)   | AUTO_INCREMENT     |
| COURSE_NAME  | VARCHAR   |                   |                    |

## ENQUIRY_STATUS
| Column Name  | Data Type | Constraints        | Notes              |
|--------------|-----------|-------------------|--------------------|
| STATUS_ID    | INTEGER   | PRIMARY KEY (PK)   | AUTO_INCREMENT     |
| STATUS_NAME  | VARCHAR   |                   |                    |


# Java Component Requirements

## I) Persistence Layer Component [Database Layer]

### 1. Entity Classes
- **AdminDetail.java**  
  Represents the `AdminDetail` entity.

- **StudentEnquiries.java**  
  Represents the `StudentEnquiries` entity.

- **Course.java**  
  Represents the `Course` entity.

- **EnquiryStatus.java**  
  Represents the `EnquiryStatus` entity.

### 2. Repository Interfaces
- **AdminDetailRepository.java**  
  Interface for performing operations on the `AdminDetail` table.

- **StudentEnquiriesRepository.java**  
  Interface for performing operations on the `StudentEnquiries` table.

- **CourseRepository.java**  
  Interface for performing operations on the `Course` table.

- **EnquiryStatusRepository.java**  
  Interface for performing operations on the `EnquiryStatus` table.


