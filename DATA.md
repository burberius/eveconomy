## General
This are information about the data used here and extracted from a Eve
Online SDE MySQL database.

## Material
Material data can be extracted with the following query and should be saved
as *src/main/resources/materials.json*:
```sql
SELECT 
    m.typeID,
    i.typeName,
    IF(i.groupID = 465, 1, 0) AS ice,
    m.materialTypeID,
    j.typeName AS materialTypeName,
    m.quantity
FROM
    invTypeMaterials m
        JOIN
    invTypes i ON m.typeID = i.typeID
        JOIN
    invTypes j ON m.materialTypeID = j.typeID
WHERE
    i.published = 1
        AND i.groupID IN (SELECT 
            groupID
        FROM
            eve.invGroups
        WHERE
            categoryID = 25 AND published = 1);
```

## Types
Save the result of the following query as *src/main/resources/types.json*:

```sql
SELECT 
    i.typeID,
    i.typeName,
    i.volume,
    i.iconID,
    i.groupID,
    g.groupName
FROM
    eve.invTypes i
        JOIN
    invGroups g ON i.groupID = g.groupID
WHERE
    i.published = 1
ORDER BY i.typeID
LIMIT 20000;
```

## Type translations
Save the result of the following query as 
*src/main/resources/typetranslations.json*:

```sql
SELECT 
    i.typeID, t.languageID, t.text
FROM
    invTypes i
        JOIN
    eve.trnTranslations t ON i.typeID = t.keyID
WHERE
    i.published = 1
        AND t.languageID IN ('de' , 'en', 'fr', 'ja', 'ru')
        AND t.tcID = 8
LIMIT 100000;
```
