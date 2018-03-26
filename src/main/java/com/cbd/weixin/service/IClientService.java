package com.cbd.weixin.service;


import com.cbd.weixin.domain.Client;

import java.util.List;

/**
 * Created by zlb on 2017.09.05.
 * 微信用户服务
 */
public interface IClientService {
    void insert(Client client);

    void delete(Long id);

    void update(Client client);

    Long getClientId(String openID);
    List<Client> listAll();

}
