using cop_mobile.Model;
using cop_mobile.ViewModel;
using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using YoutubeExplode;
using YoutubeExplode.Videos.Streams;

namespace cop_mobile.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class PlaySermon : ContentPage
    {
        private Sermon sermon;
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

        protected override void OnAppearing()
        {
            base.OnAppearing();
            MessagingCenter.Send(this, "AllowLandscape");
        }

        protected override void OnDisappearing()
        {
            base.OnDisappearing();
            MessagingCenter.Send(this, "PreventLandscape"); //during page close setting back to portrait 
        }

        private async void getVideoContent()
        {
            var youtube = new YoutubeClient();
            var streamManifest = await youtube.Videos.Streams.GetManifestAsync(sermon.url);
            var streamInfo = streamManifest.GetMuxedStreams().GetWithHighestVideoQuality();

            if (streamInfo != null)
            {
                videoPlayer.Source = streamInfo.Url;
            }
        }

        private async void closeButton_Clicked(object sender, System.EventArgs e)
        {
            videoPlayer.Stop();
            await Navigation.PopModalAsync();
        }

        private void MyMediaElement_MediaOpened(object sender, EventArgs e)
        {
            MyActivityIndicator.IsVisible = false;
        }
    }
}