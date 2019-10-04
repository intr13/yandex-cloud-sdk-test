package ru.intr13.yandexcloud.sdktest;

import yandex.cloud.api.compute.v1.InstanceOuterClass;
import yandex.cloud.api.compute.v1.InstanceServiceGrpc;
import yandex.cloud.api.compute.v1.InstanceServiceOuterClass;
import yandex.cloud.sdk.Config;
import yandex.cloud.sdk.ServiceFactory;
import yandex.cloud.sdk.auth.Auth;

import java.time.Duration;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Config config = Config.builder()
                .credentials(Auth.fromEnv(Auth::oauthToken, "YC_OAUTH"))
                .requestTimeout(Duration.ofMinutes(1))
                .build();

        ServiceFactory factory = new ServiceFactory(config);
        InstanceServiceGrpc.InstanceServiceBlockingStub instanceService = factory.create(
                InstanceServiceGrpc.InstanceServiceBlockingStub.class,
                InstanceServiceGrpc::newBlockingStub
        );

        List<InstanceOuterClass.Instance> instances = instanceService.list(
                InstanceServiceOuterClass.ListInstancesRequest.newBuilder()
                        .setFolderId(System.getenv("YC_FOLDER_ID"))
                        .build()
        ).getInstancesList();
        instances.forEach(System.out::println);
    }
}
