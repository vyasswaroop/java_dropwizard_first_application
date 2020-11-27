package com.vyasswaroop.dropwizard.introduction;

import com.vyasswaroop.dropwizard.introduction.configuration.ApplicationHealthCheck;
import com.vyasswaroop.dropwizard.introduction.configuration.BasicConfiguration;

import com.vyasswaroop.dropwizard.introduction.domain.Brand;
import com.vyasswaroop.dropwizard.introduction.repository.BrandRepository;
import com.vyasswaroop.dropwizard.introduction.resource.BrandResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.List;

public class IntroductionApplication extends Application<BasicConfiguration> {

    public static void main(String[] args) throws Exception {
        new IntroductionApplication().run("server", "introduction-config.yml");
    }

    @Override
    public void run(BasicConfiguration basicConfiguration, Environment environment) {
        int defaultSize = basicConfiguration.getDefaultSize();
        BrandRepository brandRepository = new BrandRepository(initBrands());
        BrandResource brandResource = new BrandResource(defaultSize, brandRepository);
        environment.jersey().register(brandResource);
        final ApplicationHealthCheck healthCheck = new ApplicationHealthCheck();
        environment
                .healthChecks()
                .register("application", healthCheck);
    }

    @Override
    public void initialize(Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    private List<Brand> initBrands(){
        final List<Brand> brands = new ArrayList<>();
        brands.add(new Brand(1L, "Brand1"));
        brands.add(new Brand(2L, "Brand2"));
        brands.add(new Brand(3L, "Brand3"));
        return brands;
    }
}
