package com.cbd.weixin.mapper;


import com.cbd.weixin.domain.Client;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zlb on 2017.09.05.
 */
@Mapper
public interface ClientMapper {
	void insert(Client client);

	void delete(Long id);

	void update(Client client);

	//查询取消关注的客户的id
	Long getClientId(String openID);

	List<Client> listAll();
}
