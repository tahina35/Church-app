using cop_mobile.ViewModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NotificationPage : ContentPage
    {
        private NotificationViewModel vm;

        public NotificationPage(NotificationViewModel viewModel)
        {
            InitializeComponent();
            vm = viewModel;
            this.BindingContext = vm;
        }
    }
}