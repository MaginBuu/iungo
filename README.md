# iungo

instalation guide:

    - Versions used:
        - data base: postgresql 12
        - ide: intelliJ ultimate
        - java version: 1.8

    - to create database:
            - psql at terminal 
                - (sudo -u postgres psql) on macOS or linux
                - (cd C:\Program Files\PostgreSQL\12\bin, .\psql.exe -U postgres) on windows
                    -  create database iungo;
                    -  create user iungo with encrypted password '1234';
                    -  grant all privileges on database iungo to iungo; 
                    
    - To run the project (after creating database):                    
        - open project as maven project in intelliJ
        - make sure that java 1.8 is selected at file->ProjectStructrure->ProjectJDK
        - Click on AddConfiguration or Edit configuration on the top right corner
            - Add new configuration -> tomcat Server -> local
            - on server tab:
                - In aplication server the tomcat version must be configured:
                    - path for windows: C:\Program Files\Apache Software Foundation\Tomcat 9.0
                    - path for macOS/Linux: /usr/local/Cellar/tomcat/{tomcat_version}}/libexec
                - Check that JRE is java 1.8
                - Before launch section(on the bottom):
                    - add -> build artifacts -> iungo:war
            - on deployment tab:
                - add -> artifact -> iungo:war
                - aplication context = /
        
        
    