-- SQL listar últimos 5 cursos 
SELECT c.nombre, c.identificador, nHoras AS horas, concat(p.nombre," ", p.apellidos)AS profesor 
FROM curso AS c 
LEFT JOIN profesor AS p ON p.codigo = c.profesor_codigo
ORDER BY c.codigo DESC LIMIT 5; 

-- SQL listar últimos 5 usuarios creados 
SELECT * FROM alumno ORDER BY codigo DESC LIMIT 5;

-- SQL detalle curso + número de reseñas 
SELECT c.nombre, c.identificador, c.nHoras, concat(p.nombre," ", p.apellidos) AS profesor, nHoras AS horas, COUNT(r.codigo) AS 'num reseñas'
FROM curso AS c 
LEFT JOIN resenias AS r ON c.codigo =  r.curso_codigo
JOIN profesor AS p ON p.codigo = c.profesor_codigo
GROUP BY c.codigo
ORDER BY COUNT(r.codigo) DESC;

-- SQL listado usuarios ordenado por número de reseñas - (Usuarios = alumnos)
SELECT concat(a.nombre," ",a.apellidos) AS Alumno, COUNT(r.resenia) AS 'Num Resenias' FROM alumno AS a
LEFT JOIN resenias AS r ON r.alumno_codigo = a.codigo
GROUP BY a.codigo
ORDER BY COUNT(r.resenia) DESC, a.nombre ASC;

-- SQL Resumen de número de cursos de todos los años 
SELECT year(fInicio) AS 'Año', COUNT(nombre) AS 'Cursos' 
FROM curso
GROUP BY year(fInicio)
ORDER BY year(fInicio) DESC; 