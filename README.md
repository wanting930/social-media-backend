# 社交媒體平台 (Social Media Platform)

一個基於 Spring Boot 的後端社交媒體專案，提供用戶註冊、登錄、發文、評論等功能。

## 環境要求

- **Java**: 17+
- **Maven**: 3.6.0+
- **MySQL**: 5.7+

## 技術棧

- Spring Boot 4.0.6
- Spring Security
- JWT (JJWT 0.12.6)
- MySQL

## 安裝與執行

### 資料庫設置

1. 在 `src/main/resources/application.properties` 中配置資料庫連線資訊：

```properties
spring.datasource.username=<your_mysql_username>
spring.datasource.password=<your_mysql_password>
```

2. 使用 `db/` 目錄下的 SQL 檔案初始化資料庫

### 啟動應用

```bash
mvn spring-boot:run
```