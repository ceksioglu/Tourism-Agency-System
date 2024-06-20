# Hotel Reservation System

This project is a Hotel Reservation System designed using Java Swing for the frontend and PostgreSQL for the backend. The system allows users to manage hotels, rooms, and reservations with various functionalities such as adding, updating, searching, and deleting records.

## Project Structure

The project follows a layered architecture with the following main packages:

- `core`: Contains core classes such as `DatabaseManager` and `Helper`.
- `dao`: Contains Data Access Objects (DAOs) for interacting with the database.
- `entity`: Contains entity classes representing the database tables.
- `business`: Contains business logic classes for managing entities.
- `view`: Contains Java Swing classes for the user interface.

### Directory Structure

/src/
  /core
  /dao
  /entity
  /business
  /view

## Features

### Core Package

- **DatabaseManager**: Manages the database connection using a Singleton pattern.
- **Helper**: Contains utility methods for setting up the UI, showing messages, and checking empty fields.

### DAO Package

- **HotelDAO**: Manages CRUD operations for hotels, including facilities and pension types.
- **RoomDAO**: Manages CRUD operations for rooms, including room types and availability.
- **SeasonDAO**: Manages CRUD operations for seasons.
- **ReservationDAO**: Manages CRUD operations for reservations.
- **UserDAO**: Manages CRUD operations for users.

### Entity Package

- **Hotel**: Represents a hotel with details such as name, city, region, address, email, phone, stars, facilities, and pension types.
- **Room**: Represents a room with details such as hotel ID, season ID, pension type ID, room type, bed count, size, amenities, prices, and stock.
- **Season**: Represents a season with details such as hotel ID, start date, and end date.
- **Reservation**: Represents a reservation with details such as room ID, user ID, dates, counts, prices, and guest information.
- **User**: Represents a user with details such as username, password, and role.

### Business Package

- **HotelManager**: Handles business logic for hotels, including retrieving hotels by name and managing facilities and pension types.
- **RoomManager**: Handles business logic for rooms, including retrieving rooms by ID and managing room availability.
- **SeasonManager**: Handles business logic for seasons.
- **ReservationManager**: Handles business logic for reservations, including checking room availability and managing reservations.
- **UserManager**: Handles business logic for users, including user validation and retrieval.

### View Package

- **AdminView**: UI for admin users to manage hotels, rooms, seasons, and reservations.
- **RoomView**: UI for managing rooms.
- **SeasonView**: UI for managing seasons.
- **ReservationView**: UI for managing reservations.
- **UserView**: UI for personnel users to manage their tasks.
- **LoginView**: UI for user login.

## Setup

1. Clone the repository.
2. Set up the PostgreSQL database and run the provided SQL scripts to create the schema and insert initial data.
3. Update the database connection details in `DatabaseManager.java`.
4. Run the application from `LoginView.java`.

## Usage

- **Admin**: Can manage hotels, rooms, seasons, and reservations.
- **Personnel**: Can manage their tasks related to reservations.

## Mock Data

- The project comes with mock data for hotels, rooms, seasons, and reservations to get you started quickly.
- Hotel IDs range from 1 to 61.
- Facility and pension type IDs are pre-defined for reference.

## Note

Ensure the sequences for IDs are properly set up after resetting the database to avoid conflicts.

---

Feel free to modify the content as per your project's specifics and requirements.
