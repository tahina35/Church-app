using mobile.Model;
using mobile.ViewModel;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using YoutubeExplode;
using YoutubeExplode.Videos.Streams;

namespace mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class PlaySermon : ContentPage
    {
        Sermon sermon;
        public PlaySermon(Sermon sermon)
        {
            InitializeComponent();
            this.sermon = sermon;
            try
            {
                getVideoContent();
            }
            catch (Exception e)
            {
                Console.Write(e.Message);
            }

        }

        private async void getVideoContent()
        {
            var youtube = new YoutubeClient();

            //var video = await youtube.Videos.GetAsync(sermon.URL);
            var streamManifest = await youtube.Videos.Streams.GetManifestAsync(sermon.URL);
            var streamInfo = streamManifest.GetMuxed().WithHighestVideoQuality();

            if (streamInfo != null)
            {
                //var stream = await youtube.Videos.Streams.GetAsync(streamInfo);
                MyMediaElement.Source = streamInfo.Url;
            }
        }

        private async void closeButton_Clicked(object sender, System.EventArgs e)
        {
            MyMediaElement.Stop();
            await Navigation.PopModalAsync();
        }

        private void MyMediaElement_MediaOpened(object sender, EventArgs e)
        {
            MyActivityIndicator.IsVisible = false;
        }
    }
}