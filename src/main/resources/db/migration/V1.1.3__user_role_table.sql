create table user_roles
(
  user_role_id serial not null
    constraint user_roles_pkey
    primary key,
  email varchar(20) not null
    constraint user_roles_email_fkey
    references person (email),
  role varchar(20) not null,
  constraint user_roles_email_role_key
  unique (email, role)
);

INSERT INTO public.user_roles (user_role_id, email, role) VALUES (1, 'nana@gmail.com', 'ROLE_USER');