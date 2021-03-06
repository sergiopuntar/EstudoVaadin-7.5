-- Tablespace sistema financeiro
CREATE TABLESPACE TBSDATA01_SGPF_FNC DATAFILE 'TBSDATA01_SGPF_FNC.DBF'
SIZE 256M
AUTOEXTEND ON NEXT 64M MAXSIZE 1024M
LOGGING ONLINE PERMANENT BLOCKSIZE 8192
EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT 
NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO;

CREATE TABLESPACE TBSIDX01_SGPF_FNC DATAFILE 'TBSIDX01_SGPF_FNC.DBF'
SIZE 128M
AUTOEXTEND ON NEXT 32M MAXSIZE 512M
LOGGING ONLINE PERMANENT BLOCKSIZE 8192
EXTENT MANAGEMENT LOCAL AUTOALLOCATE DEFAULT 
NOCOMPRESS  SEGMENT SPACE MANAGEMENT AUTO;

-- Roles sistema financeiro
CREATE ROLE ROWNER_SGPF_FNC;
CREATE ROLE RUSER_SGPF_FNC;

GRANT CONNECT, RESOURCE TO ROWNER_SGPF_FNC;
GRANT CONNECT TO RUSER_SGPF_FNC;

-- Usuário de schema sistema financeiro
CREATE USER SGPF_FNC IDENTIFIED BY SGPF_FNC
DEFAULT TABLESPACE TBSDATA01_SGPF_FNC
TEMPORARY TABLESPACE TEMP
QUOTA UNLIMITED ON TBSDATA01_SGPF_FNC
QUOTA UNLIMITED ON TBSIDX01_SGPF_FNC;

GRANT ROWNER_SGPF_FNC TO SGPF_FNC;

-- Usuário de aplicação sistema financeiro
CREATE USER SGPF IDENTIFIED BY SGPF
DEFAULT TABLESPACE USERS
TEMPORARY TABLESPACE TEMP;

GRANT RUSER_SGPF_FNC TO SGPF;
ALTER USER SGPF DEFAULT ROLE RUSER_SGPF_FNC;
