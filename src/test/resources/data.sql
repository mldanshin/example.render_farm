insert into users (`id`, `login`, `password`)
    values (1, 'login1', 'password1');
insert into users (`id`, `login`, `password`)
    values (2, 'login2', 'password2');
insert into users (`id`, `login`, `password`)
    values (3, 'login3', 'password3');
insert into users (`id`, `login`, `password`)
    values (4, 'login4', 'password4');

insert into tasks (`id`, `name`, `status`, `user_id`)
    values (1, 'task1', 0, 3);
insert into tasks (`id`, `name`, `status`, `user_id`)
    values (2, 'task2', 1, 3);
insert into tasks (`id`, `name`, `status`, `user_id`)
    values (3, 'task3', 0, 3);
insert into tasks (`id`, `name`, `status`, `user_id`)
    values (4, 'task4', 0, 4);
insert into tasks (`id`, `name`, `status`, `user_id`)
    values (5, 'task5', 0, 4);

insert into history_tasks (`id`, `tasks_id`, `status`, `date_time`)
    values (1, 4, 0, '2022-11-11T17:36:00');