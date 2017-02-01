This are information about the data used here and extracted from a Eve
Online SDE MySQL database.

Material data can be extracted with the following query and should be saved
as *src/main/resources/materials.json*:
```
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
