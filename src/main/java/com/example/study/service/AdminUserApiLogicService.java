package com.example.study.service;

import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserAPiRequest;
import com.example.study.model.network.response.AdminUserAPiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserAPiRequest, AdminUserAPiResponse, AdminUser> {

    @Override
    public Header<AdminUserAPiResponse> create(Header<AdminUserAPiRequest> request) {
        AdminUserAPiRequest adminUserAPiRequest = request.getData();
        AdminUser adminUser = AdminUser.builder()
                .account(adminUserAPiRequest.getAccount())
                .password(adminUserAPiRequest.getPassword())
                .status(adminUserAPiRequest.getStatus())
                .role(adminUserAPiRequest.getRole())
                .lastLoginAt(adminUserAPiRequest.getLastLoginAt())
                .passwordUpdatedAt(adminUserAPiRequest.getPasswordUpdatedAt())
                .loginFailCount(adminUserAPiRequest.getLoginFailCount())
                .registeredAt(LocalDateTime.now())
                .build();
        AdminUser newAdminUser = baseRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserAPiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserAPiResponse> update(Header<AdminUserAPiRequest> request) {
        AdminUserAPiRequest adminUserAPiRequest = request.getData();
        return baseRepository.findById(adminUserAPiRequest.getId())
                .map(adminUser -> {
                    adminUser.setAccount(adminUserAPiRequest.getAccount())
                            .setPassword(adminUserAPiRequest.getPassword())
                            .setStatus(adminUserAPiRequest.getStatus())
                            .setRole(adminUserAPiRequest.getRole())
                            .setLastLoginAt(adminUserAPiRequest.getLastLoginAt())
                            .setPasswordUpdatedAt(adminUserAPiRequest.getPasswordUpdatedAt())
                            .setLoginFailCount(adminUserAPiRequest.getLoginFailCount())
                            .setRegisteredAt(adminUserAPiRequest.getRegisteredAt())
                            .setUnregisteredAt(adminUserAPiRequest.getUnregisteredAt());
                    AdminUser saveAdminUser = baseRepository.save(adminUser);
                    return saveAdminUser;
                })
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id).map(adminUser -> {
            baseRepository.delete(adminUser);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<AdminUserAPiResponse> response(AdminUser adminUser) {
        AdminUserAPiResponse adminUserAPiResponse = AdminUserAPiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .build();
        return Header.OK(adminUserAPiResponse);
    }
}
