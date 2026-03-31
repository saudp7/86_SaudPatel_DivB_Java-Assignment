# Assignment 2 - Java Servlets

## Folder Contents
- `Ass-2_P01/` - Fashion Store web application (Servlet-based)
- `DB Script.txt` - schema/setup script

## Key Features Implemented
- Login, register, forgot-password flow
- Captcha utility for registration
- Authentication filter to protect internal routes
- Product and category management (add/update/delete/find)
- Shopping flow with cart and bill generation
- Session tracking using listeners
- Reporting servlet features

## Important Source Areas
- Controllers/Servlets: `src/main/java/com/mycompany/ass_p01/controller/`
- Filter: `src/main/java/com/mycompany/ass_p01/filter/AuthFilter.java`
- Listeners: `src/main/java/com/mycompany/ass_p01/listener/`
- DAO layer: `src/main/java/com/mycompany/ass_p01/dao/`

## Run Notes
- Maven web project (`pom.xml` present).
- Open in NetBeans, run on configured Jakarta-compatible server.
- Execute `DB Script.txt` before testing DB features.

