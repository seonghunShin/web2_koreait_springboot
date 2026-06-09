create table oauth2_user(
	oauth2_user_id int not null auto_increment,
    user_id varchar(36) not null,
    provider varchar(50) not null,
    provider_user_id varchar(200) not null,
    create_at datetime not null default current_timestamp,
    update_at datetime not null default current_timestamp on update current_timestamp,
    primary key (oauth2_user_id),
    constraint unique_oauth2_provider_user unique (provider, provider_user_id),
    constraint fk_oauth2_user_user foreign key (user_id) references users(user_id)
);