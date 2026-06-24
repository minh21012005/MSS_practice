CREATE DATABASE FUCarRentingSystem;
GO

USE FUCarRentingSystem;
GO

CREATE TABLE Supplier (
    SupplierID INT PRIMARY KEY IDENTITY(1,1),
    SupplierName NVARCHAR(255) NOT NULL,
    SupplierDescription NVARCHAR(MAX),
    SupplierAddress NVARCHAR(500)
);

CREATE TABLE Manufacturer (
    ManufacturerID INT PRIMARY KEY IDENTITY(1,1),
    ManufacturerName NVARCHAR(255) NOT NULL,
    Description NVARCHAR(MAX),
    ManufacturerCountry NVARCHAR(255)
);

CREATE TABLE CarInformation (
    CarID INT PRIMARY KEY IDENTITY(1,1),
    CarName NVARCHAR(255) NOT NULL,
    CarDescription NVARCHAR(MAX),
    NumberOfDoors INT,
    SeatingCapacity INT,
    FuelType NVARCHAR(50),
    Year INT,
    ManufacturerID INT,
    SupplierID INT,
    CarStatus INT,
    CarRentingPricePerDay DECIMAL(18,2),
    FOREIGN KEY (ManufacturerID) REFERENCES Manufacturer(ManufacturerID),
    FOREIGN KEY (SupplierID) REFERENCES Supplier(SupplierID)
);

CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY IDENTITY(1,1),
    CustomerName NVARCHAR(255) NOT NULL,
    Telephone NVARCHAR(50),
    Email NVARCHAR(255) UNIQUE NOT NULL,
    CustomerBirthday DATE,
    CustomerStatus INT,
    Password NVARCHAR(255) NOT NULL
);

CREATE TABLE RentingTransaction (
    RentingTransationID INT PRIMARY KEY IDENTITY(1,1),
    RentingDate DATETIME,
    TotalPrice DECIMAL(18,2),
    CustomerID INT,
    RentingStatus INT,
    FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

CREATE TABLE RentingDetail (
    RentingTransactionID INT,
    CarID INT,
    StartDate DATETIME,
    EndDate DATETIME,
    Price DECIMAL(18,2),
    PRIMARY KEY (RentingTransactionID, CarID),
    FOREIGN KEY (RentingTransactionID) REFERENCES RentingTransaction(RentingTransationID),
    FOREIGN KEY (CarID) REFERENCES CarInformation(CarID)
);
GO
