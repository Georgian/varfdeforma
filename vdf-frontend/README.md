# "Vârf de Formă" https://varfdeforma.ro

> Rougly translated to "Peak Performance", VDF is a personal project written with love and care written in little spare time. 

## Tutorials

https://dev.to/adnanrahic/a-crash-course-on-serverless-side-rendering-with-vuejs-nuxtjs-and-aws-lambda-1nk4

## Setup new AWS account

1. Create an IAM user with policies: 

   - AWSCertificateManagerReadOnly
   - AmazonAPIGatewayAdministrator
   - AmazonAPIGatewayInvokeFullAccess
   - CloudFrontFullAccess
   - AmazonRoute53ReadOnlyAccess
   - AmazonS3FullAccess
   - CloudWatchLogsFullAccess
   - IAMFullAccess
   - AWSLambdaFullAccess
   
   and this custom user policy:
   
   {
       "Version": "2012-10-17",
       "Statement": [
           {
               "Sid": "Stmt1449904348000",
               "Effect": "Allow",
               "Action": [
                   "cloudformation:CreateStack",
                   "cloudformation:UpdateStack"
               ],
               "Resource": [
                   "*"
               ]
           }
       ]
   }
   
2. Create a Hosted Zone in Route53 with the domain name
3. Request a certificate from AWS Certificate Manager with *.varfdeforma.ro and varfdeforma.ro, 
   then create a CNAME Record Set with domains listed in the certificate. This can be created
   automatically from the Certificate Manager by "Create record in Route 53" button.
4. sls create_domain
5. Remember to update nameservers in domain administration (in my case: ROTLD.RO)
  
# Setup For developing on new machines:

1. npm i -g serverless
2. serverless config credentials --provider aws --key xxxxxxxxxxxxxx --secret xxxxxxxxxxxxxx

## Deploying

npm run deploy
