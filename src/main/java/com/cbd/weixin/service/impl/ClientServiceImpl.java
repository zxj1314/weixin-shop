package com.cbd.weixin.service.impl;

import com.cbd.weixin.domain.Client;
import com.cbd.weixin.mapper.ClientMapper;
import com.cbd.weixin.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zlb on 2017.09.05.
 */
@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientMapper clientMapper;
    @Override
    public void insert(Client client) {
        clientMapper.insert(client);
    }

    @Override
    public void delete(Long id) {
        clientMapper.delete(id);
    }

    @Override
    public void update(Client client) {
        clientMapper.update(client);
    }

    @Override
    public Long getClientId(String openID) {
        return clientMapper.getClientId(openID);
    }

	@Override
	public List<Client> listAll() {
		return clientMapper.listAll();
	}
}
