package com.api.gateway.service.users;

import com.api.gateway.model.dto.consume.ConsumeJsonLogin;
import com.api.gateway.model.dto.consume.ConsumeJsonString;
import com.api.gateway.model.dto.response.ResponseJsonLogin;
import com.api.gateway.model.dto.response.ResponseJsonString;

public interface UserService {
    String bcrypt(String password);

    ResponseJsonString bcrypt(ConsumeJsonString consume);

    ResponseJsonLogin login(ConsumeJsonLogin consume);


}
