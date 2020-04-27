create extension if not exists pgcrypto;

update usr set password = crypt(password, gen_salt('bf', 8));-- gen_salt - доп значение, присоединяемое к паролю при шифровании, bf - алгоритм шифрования --