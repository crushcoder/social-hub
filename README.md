# social-hub
Batch Job that grabs (public) content from my so-called social feeds like wordpress, flickr, youtube, facebook and creates html snippets to be included in my homepage.

In contrast to other projects that make your homepage the hub of my philosophy is that i use the certain services as the originals.
Other projects (or CMSs with plugins) post the original content like a new video on your homepage and share this to the social media services.

I rather post the video on Youtube and then have it in the News section of my page.
And post automatically on e.g. Facebook that i have a new video on Youtube.
This means that i don't need storage space for the video on my webspace and i don't need my own video player and streamer.

This is the cheaper solution, although not a as free because you still give away your content.
But my webpage lives more in that there are news, and i can use the services newest features, their apps aso.

I also didn't want to have the widgets of the services on my page, which will be slower and don't blend in or even track the user.


## Configuration

You need either a application.properties file besides the jar or some parameters while starting or some system properties
set.

Depending on the feeds you want to grab you have to set certain values there.
If some property is not set the plugin won't do something.
 
* Youtube
    * youtube.apikey: You have to create an application to use the youtube api. https://developers.google.com/youtube/v3/getting-started.
    * youtube.playlistid: That's the playlist the newest videos are taken from. If you click on your youtube page besides 'Uploads' on 'Play all' you will find the playlist id for all your videos in the url. Example: 'UUpLVP1s0eE8sL5-246C4ZNg'
    * (optional) youtube.count: How many videos of the playlist should be shown. Default is 3.
    * (optional) youtube.headline: That's the headline used in the html output for the news section. Default is 'Youtube'
* Flickr
    * flickr.apikey: The private api key for you. Get one here: https://www.flickr.com/services/apps/create/apply/
    * flickr.userid: Your user id. You can find it on your flickr page in the url. Example: '99480279@N08'
    * (optional)flickr.count: How many pictures should be grabbed and shown, default is 3.
    * (optional) flickr.headline: Default is 'Flickr'
* Wordpress.com
    * wordpress.sites: The blogs the posts should be taken from. If you have more than one you can list them comma separated. E.g 'levinte.wordpress.com,livingsheep.wordpress.com'
    * (optional) wordpress.count: How many posts per site should be shown, defaul is three
    * (optional) wordpress.headline: Default is 'Blog'
    
For the output:

* Write a local html file
    * local.filewriter.filename: absolute or relative path and filename for the file to be written.
* Upload html file to FTP Server. We expect a FTP/SSL Server
    * ftp.writer.server: Hostname/IP/Url of your FTP Server
    * ftp.writer.username: Username on the server
    * ftp.writer.password: Password on the server
    * (Optional) ftp.writer.directory: The directory on the FTP server that the file will be created in. Default is the root directory '/'.
    * (Optional) ftp.writer.filename: The filename that will be used, default is 'news.html'
    * (Optional) ftp.writer.port: Port of the FTP server, default is 21.
    
## Run

You can either run the jar file or an docker image. In both cases you have to provide the configuration via command line.

### the jar file

    java -jar social-hub-1.0.0-SNAPSHOT.jar --youtube.apikey='your.api.key' --youtube.playlistid='your.playlist.id' --local.filewriter.filename='news.html'

### the docker image

    docker run -e "youtube_apikey=your.api.key" -e "youtube_playlistid=your.playlist.id" [...] develcab/socialhub
    
I expect that you won't use this to generate a local file but to upload via FTP or to S3.
The configuration have to be provided by environment variables, therefore the dot "." that is used in Java has to be replaced by an underscore "_".

## Build

For running all tests you have to set some of the properties above.
For the content (playlist, public account) there are my ones set as default, but the api keys to some services are private.
So you will have to create for following services the api keys:

They have to be set as system properties.

* Youtube
    * youtube_apikey
* Flickr
    * flickr_apikey

You also have to have a FTP Server to upload a file to.
The test expects that the uploaded file can be downloaded via Http afterwards.
If you want to use the SocialHub yourself with uploading via FTP you should definitely look that
here for the tests another file is used than your real one!

* FTP Server
    * ftp_writer_directory
    * ftp_writer_filename
    * ftp_writer_server
    * (Optional) ftp_writer_port
    * ftp_writer_username
    * ftp_writer_password
    * ftp_writer_test_httpurl: The url the test will find the uploaded file. Has to work in a normal Webbrowser.
