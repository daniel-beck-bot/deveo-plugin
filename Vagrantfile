# -*- mode: ruby -*-
# vi: set ft=ruby :
Vagrant.configure(2) do |config|

  config.vm.box = "ubuntu/trusty64"
  config.vm.network "public_network", bridge: "en0: Wi-Fi (AirPort)"
  config.vm.provider "virtualbox" do |vb|
    vb.memory = "1024"
  end

  config.vm.provision "shell", inline: <<-SHELL
    wget -q -O - https://jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
    sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
    sudo apt-get update
    sudo apt-get install openjdk-7-jdk jenkins maven -y

    # Run Jenkins as the vagrant user
    sudo sed -i 's/^JENKINS_USER.*/JENKINS_USER=vagrant/;s/^JENKINS_GROUP.*/JENKINS_GROUP=vagrant/' /etc/default/jenkins
    sudo chown -R vagrant.vagrant /var/cache/jenkins /var/lib/jenkins /var/log/jenkins

    # configure settings.xml as mentioned in the prerequisites
    cat <<EOF > ~/.m2/settings.xml
<settings>
  <profiles>
    <profile>
      <id>compiler</id>
        <properties>
          <JAVA_1_7_HOME>/usr/lib/jvm/java-7-openjdk-amd64</JAVA_1_7_HOME>
        </properties>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>compiler</activeProfile>
  </activeProfiles>
</settings>
EOF

    sudo service jenkins restart
    sudo update-rc.d jenkins enable
  SHELL
end

