# Jim Abraham's Abacus Demo #

(I'm gonna treat you and me as long-time professionals here. Whatever I'm leaving out, you should assume it's because frankly I don't have the time to indulge in an orgy of typing, and not because I don't know it. Please try me.)

As instructed, here's a SpringBoot REST service that conforms to the following specification. It accepts (and responds with JSON payloads) to the following endpoints:

* PUT    /resources/data           # creates Person from JSON payload, returns Person containing new object id.
* GET    /resources/data/{data_id} # returns Person if found, otherwise 404.
* DELETE /resources/data/{data_id} # deletes Person, returns deleted Person

JSON attributes as follows:

{
  resourceType : Person,
  id : string,
  name : string,
  age : integer,
  locale : string
}


## Development ##


For this trivial use case, I could have done the entire thing in 200 lines of Perl without a single dependency, including self-tests,
but as SpringBoot was requested, I went and looked at it. You said Java artifacts so I wrote it that way instead of in Groovy.

Spring didn't start up as a convention over configuration MVC web framework precisely. I used it years ago, principally as a way never to look at Struts again, but with SpringBoot they've produced the now age-old MVC C-over-C web development pattern familiar from RubyOnRails, Django, node.js, Grails (built on Spring, I've written this for years) and lots more. 


## Building/Deploying ##

We're both long time Java guys, we know Maven dependency management is excellent, and Gradle is a great build tool. It's got a build.gradle, with the usual targets. So if you wanted to try it out, there's several options:

* (Developer) Clone this thing, make sure you've got Java and Gradle installed (use SDKMAN!), gradle bootRun, run curl against localhost:8080 with the endpoints above. Advantages: it's what the kids know from GitHub, you can hack on it. Disadvantages: knowing things and needing to type things.

* (Developer) Publish to maven library. I haven't bothered to get that fancy.

* With Java installed, execute java -jar jca-abacus-demo.jar. This will run standalone.

* Install abacus-basic-demo shell script (or Windows .bat) into your bin directory, install jca-abacus-demo.jar into your lib directory, execute abacus-basic-demo

* Drop the jar into your favorite servlet container. Advantages: little programming expertise required, developer maintains control over development, quality, and IP protection by shipping (obfuscated) class files. 

* Install docker-ce and run: docker run -p 5000:8080 jcabraham/jca-abacus-demo:0.1.0-SNAPSHOT, which will pull from Docker Hub and run it. Advantages: deployment couldn't be simpler. Disadvantages: quite a lot, actually, for anything beyond the simplest project. We should talk about "Docker considered harmful."

* Run this in Elastic Beanstalk or deploy to an ec2 autoscaling group or deploy your Docker container likewise.

## Security ##

* It's a webserver, so implement the usual security: run as nobody or an equivalent limited perm account.

* Run over SSL(https) for end-to-end encryption (encrpyt your database as while you're at it).

* Get a trusted cert so your users can trust your url.

* Password/OAUTH security: easy to implement with Spring Security. We can talk about federation, etc if necessary.

* For the machine/VM, set appropriate firewall rules.

* Consider a hardened VM/AMI to further protect your data and underlying infrastructure.

## Monitoring ##

* Simple cron jobs or monitoring software like nagios can verify it's up and responding.

* Log analysis tools.

* (If on AWS) turn on CloudWatch, see log analysis above.

## Scaling ##

* Upscale your instance if possible. (KISS)

* Run several instances behind a load-balancer.

* (If AWS) Consider deploying to an autoscaling group, modifying to run in Elastic Beanstalk, or (best) re-write this as AWS Lambda functions.

