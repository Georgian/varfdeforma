FROM node:13-alpine3.10

# create destination directory
RUN mkdir -p /usr/src/vdf-frontend
WORKDIR /usr/src/vdf-frontend

# update and install dependency
RUN apk update && apk upgrade
RUN apk add git

# copy the app, note .dockerignore
COPY . /usr/src/vdf-frontend/
RUN npm install

# build necessary, even if no static files are needed,
# since it builds the server as well
RUN npm run build

# expose 3000 on container
EXPOSE 3000

# set app serving to permissive / assigned. No need for nginx if we have this
ENV NUXT_HOST=0.0.0.0
# set app port
ENV NUXT_PORT=3000

# start the app
CMD [ "npm", "start" ]