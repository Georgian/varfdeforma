FROM node:10-alpine3.10

# update and install dependency
RUN apk update && apk upgrade
# RUN apk add git
RUN apk add python make g++

# create destination directory
RUN mkdir -p /usr/src/vdf-frontend
WORKDIR /usr/src/vdf-frontend

# copy the app, note .dockerignore
COPY . /usr/src/vdf-frontend/
RUN npm install

ARG BACKEND_URL
ENV BACKEND_URL ${BACKEND_URL}
ARG FRONTEND_URL
ENV FRONTEND_URL ${FRONTEND_URL}
#ENV BACKEND_URL=https://api.varfdeforma.ro
#ENV FRONTEND_URL=https://varfdeforma.ro

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
