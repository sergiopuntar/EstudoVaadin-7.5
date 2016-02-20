/**
 * Script de criação da função auxiliar de geração de GUIDs.
 * Essa função gera GUIDs no formato esperado para os identificadores das tabelas.
 */
CREATE OR REPLACE FUNCTION SYS_DASHED_GUID RETURN VARCHAR2 AS 
BEGIN
  RETURN regexp_replace(rawtohex(sys_guid()), '([A-F0-9]{8})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{4})([A-F0-9]{12})', '\1-\2-\3-\4-\5');
END SYS_DASHED_GUID;

GRANT EXECUTE ON DASHED TO PUBLIC;
