ARG ERIC_ENM_SLES_EAP7_IMAGE_NAME=eric-enm-sles-eap7

ARG ERIC_ENM_SLES_EAP7_IMAGE_REPO=armdocker.rnd.ericsson.se/proj-enm

ARG ERIC_ENM_SLES_EAP7_IMAGE_TAG=1.64.0-32

FROM ${ERIC_ENM_SLES_EAP7_IMAGE_REPO}/${ERIC_ENM_SLES_EAP7_IMAGE_NAME}:${ERIC_ENM_SLES_EAP7_IMAGE_TAG}

ARG ADP_DEV_REPO_URL=arm.sero.gic.ericsson.se/artifactory/proj-ldc-repo-rpm-local/adp-dev/adp-build-env/latest
ARG SLES_DEV_TOOLS_REPO=repo-dev-tools
ARG SLES_BASE_PACKAGE_UPDATES_REPO_URL=arm.sero.gic.ericsson.se/artifactory/proj-suse-repos-rpm-local/SLE15/SLE-15-SP3-Module-Development-Tools

ARG BUILD_DATE=unspecified
ARG IMAGE_BUILD_VERSION=unspecified
ARG GIT_COMMIT=unspecified
ARG ISO_VERSION=unspecified
ARG RSTATE=unspecified

LABEL \
com.ericsson.product-number="CXC 174 1910" \
com.ericsson.product-revision=$RSTATE \
enm_iso_version=$ISO_VERSION \
org.label-schema.name="ENM Cenmproxy Service Group" \
org.label-schema.build-date=$BUILD_DATE \
org.label-schema.vcs-ref=$GIT_COMMIT \
org.label-schema.vendor="Ericsson" \
org.label-schema.version=$IMAGE_BUILD_VERSION \
org.label-schema.schema-version="1.0.0-rc1"


RUN zypper addrepo -p 50 -C -G -f https://${ADP_DEV_REPO_URL} repo-oss-adpdev
RUN zypper addrepo -p 50 -C -G -f https://${SLES_BASE_PACKAGE_UPDATES_REPO_URL} SLES_DEV_TOOLS_REPO

RUN mkdir -p /opt/ericsson/bro-proxy

RUN zypper install -y ERICserviceframework4_CXP9037454 \
ERICmodelserviceapi_CXP9030594 \
ERICmodelservice_CXP9030595 \
ERICvaultloginmodule_CXP9036201 \
ERICserviceframeworkmodule4_CXP9037453 \
ERICpostgresqljdbc_CXP9031176 \
sles_base_os_repo:postgresql15


COPY --chown=jboss_user:root code/cenmbroproxy/cenmbroproxy-war/target/cenmbroproxy.war /ericsson/3pp/jboss/standalone/deployments/

COPY --chown=jboss_user:root image_content/jboss-as.conf /ericsson/3pp/jboss/

RUN /bin/mkdir -p /ericsson/credm/data/certs && \
    /bin/chown -R root:root /ericsson/credm/data/certs && \
    /bin/chmod -R 755 /ericsson/credm/data/certs

COPY image_content/createCertificatesLinks.sh /ericsson/3pp/jboss/bin/pre-start/createCertificatesLinks.sh
RUN /bin/chmod 755 /ericsson/3pp/jboss/bin/pre-start/createCertificatesLinks.sh
# to make createCertificatesLinks.sh script work


ENV ENM_JBOSS_SDK_CLUSTER_ID="cenmproxy" \
    ENM_JBOSS_BIND_ADDRESS="0.0.0.0" \
    JBOSS_CONF="/ericsson/3pp/jboss/app-server.conf" \
    CLOUD_DEPLOYMENT="true"


EXPOSE 4447 8009 8080 9990 9999 9600
