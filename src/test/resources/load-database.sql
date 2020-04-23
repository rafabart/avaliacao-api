insert into public.category (description) VALUES
        ('Comportamental')
        ,('Programação')
        ,('Qualidade')
        ,('Processos')
;


insert into public.user_system (name,login,password) VALUES
    ('Administrador','admin','$2y$12$wZGPK1hI.9Hjff47JzttMevlNCdVBS7ldXPgr9fwciBZvW0zTmWZ2')
;


insert into public.course (description,end_date,number_of_students,start_date,category_id) VALUES
    ('Curso de Liderança','2020-04-28',20,'2020-04-25',1)
    ,('Curso de Metodos Ageis','2020-05-02',20,'2020-05-01',3)
    ,('Curso de Spring','2020-04-24',50,'2020-04-23',2)
;