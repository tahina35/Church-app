using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;
using cop_mobile.CustomComponent;
using System.ComponentModel;
using Android.Graphics.Drawables;
using cop_mobile.Droid.CustomRenderer;

[assembly: ExportRenderer(typeof(CustomEntry), typeof(CustomEntryRenderer))]
namespace cop_mobile.Droid.CustomRenderer
{
    public class CustomEntryRenderer : EntryRenderer
    {
        public CustomEntryRenderer() : base(Android.App.Application.Context) 
        { 
        }

        protected override void OnElementChanged(ElementChangedEventArgs<Entry> e)
        {
            base.OnElementChanged(e);
            if (Control != null)
            {
                Control.Background = null;
                Control.SetBackgroundColor(Android.Graphics.Color.Transparent);
            }
        }
    }
}   