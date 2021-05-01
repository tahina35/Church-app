using Foundation;
using mobile.iOS.CustomPageRenderer;
using mobile.View;
using System;
using UIKit;
using Xamarin.Forms;
using Xamarin.Forms.Platform.iOS;

[assembly: ExportRenderer(typeof(PlaySermon), typeof(PlaySermonPageRenderer))]
namespace cop_mobile.iOS.CustomPageRenderer
{
    public class PlaySermonPageRenderer : PageRenderer
    {
        public override void ViewWillDisappear(bool animated)
        {
            base.ViewWillDisappear(animated);
            UIDevice.CurrentDevice.SetValueForKey(NSNumber.FromNInt((int)(UIInterfaceOrientation.Portrait)), new NSString("orientation"));
        }
    }
}
