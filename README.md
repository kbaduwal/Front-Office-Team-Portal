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
- **AdminDetailEntity.java**  
  Represents the `AdminDetail` entity.

- **StudentEnquiriesEntity.java**  
  Represents the `StudentEnquiries` entity.

- **CourseEntity.java**  
  Represents the `Course` entity.

- **EnquiryStatusEntity.java**  
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

## II) DTO Layer [Bindind Form Data/ Binding Layer]  

- **LoginForm.java**  
  Capturing the data from the `Login` Form.

- **SignUpForm.java**  
  Capturing the data from the `SignUp` Form.

- **UnlockForm.java**  
  Capturing the data from the `Unlock` Form.

- **DashBoardResponse.java**  
  Capturing the data from the `Dashboard` of the admin.

- **EnquiryForm.java**  
  Capturing the data from the `Enqury` Form which is modified by admin.

- **EnquirySearchCriteria.java**  
  Capturing the data from the `DropDown` filter.

## III) Service Layer Component [Business Logic Layer]

### 1. Service Interface
- **UserService.java**  
  Deals with `Login, SignUp, UnlockAccount, ForgotPassword` interface.

- **EnquiryService.java**  
  Deals with `AddingEnquiry, GettingEnquiry, Edit/Update` interface.

### 2. Implent Interfaces
- **UserServiceImpl.java**  
  Implemention abstract method of the `UserService` interface.

- **EnquiryServiceImpl.java**  
  Implemention abstract method of the `EnquiryService` interface.


## IV) Helper Layer Component [Utility Classes]
- **PasswordUtils.java** 

- **EmailUtils.java**  


## v) Controller Layer Component [Taking Request, Giving Response]

- **IndexController.java**  
  Used to load `Home Page` only.

- **UserController.java**  
  Funcationalities related with the user `LogIn, SignUp, ForgotPassword, UnlockAccount`.

- **EnquiryController.java**  
  Funcationalities related with the `Enquiry`.  



# UI [Pages]
- **index.html** 
- **login.html** 
- **signup.html** 
- **unlock.html** 
- **forgotPassword.html** 
- **dashboard.html** 
- **addEnquiry.html** 
- **viewEnquiry.html** 
- **errorPage.html** 



