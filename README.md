## Fresh prod server setup (e.g. Ubuntu 18.04 droplet on DigitalOcean; but this could work for AWS too)

1. Initial server setup https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-18-04
2. Install and configure Nginx https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-ubuntu-18-04
3. Secure nginx with letsencrypt https://www.digitalocean.com/community/tutorials/how-to-secure-nginx-with-let-s-encrypt-on-ubuntu-18-04
4. Install docker on digitalocean's remote ubuntu
(https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-18-04)
- sudo apt update
- sudo apt install apt-transport-https ca-certificates curl software-properties-common
- curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
- sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
- sudo apt update
- apt-cache policy docker-ce
- sudo apt install docker-ce
- sudo systemctl status docker
5. docker-compose:
(https://docs.docker.com/compose/install/#install-compose)
- sudo curl -L "https://github.com/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
- sudo chmod +x /usr/local/bin/docker-compose

## CircleCi
### Environment variables
- DEPLOY_HOST -> IP of digitalocean droplet
- DEPLOY_USER -> usually 'root' if other user not configured
- DOCKER_LOGIN -> docker hub credentials, if other service not used (e.g. quay.io)
- DOCKER_PWD 
- RAW_REPO_URL -> used by deploy.sh to pull docker-compose.yaml straight from github

