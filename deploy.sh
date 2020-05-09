#!/bin/bash
# Georgian Grec
# Deployment script for microservices

# usage: ./deploy.sh -h [hostname]
#                    -u [username]
#                    -r [repository base url to the raw text content]

# Emojis
CLIPBOARD="📋"
CHECKMARK="✅"
RENAME="🏷️"
SCISSORS="✂️"
DOWNLOAD="📥"
BROOM="🧹"
CONSTRUCTION="🏗️"
START="🏁"

# ssh config
cat >> ~/.ssh/config  << EOF
VerifyHostKeyDNS yes
StrictHostKeyChecking no
EOF

# params
hostname=''
username=''

while getopts 'd:h:u:r:' flag; do
    case "${flag}" in
        h) hostname="${OPTARG}" ;;
        u) username="${OPTARG}" ;;
        r) repository_base_url="${OPTARG}" ;;
        *) printf "Usage..."
           exit 1 ;;
    esac
done

printf "$CLIPBOARD Attempting to create configuration folder...\n"

ssh "$username@$hostname" /bin/bash << EOF

printf "\n$SCISSORS  Pruning Docker images, networks and volumes...\n\n"
docker system prune -f

printf "$DOWNLOAD Downloading the docker-compose configuration...\n\n"
printf "Pulling config from $repository_base_url\n\n"
curl "$repository_base_url"/docker-compose.yaml --output docker-compose.yaml
mkdir -p nginx && curl "$repository_base_url"/nginx/default.conf --output default.conf

printf "\n$BROOM Stopping and removing containers and volumes...\n\n"
docker-compose down -v

printf "\n$DOWNLOAD Pulling the relevant Docker images...\n\n"
docker-compose pull

printf "\n$CONSTRUCTION  Creating containers...\n\n"
docker-compose up --no-start

printf "\n$START Starting images...\n\n"
docker-compose start
EOF
