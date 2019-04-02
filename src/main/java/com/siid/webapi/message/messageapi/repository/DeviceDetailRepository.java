package com.siid.webapi.message.messageapi.repository;

import com.siid.webapi.message.messageapi.domain.DeviceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceDetailRepository extends JpaRepository<DeviceDetailEntity, Integer> {



}
