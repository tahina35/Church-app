using cop_mobile.ViewModel;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace cop_mobile.Pages
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NewsPage : ContentPage
    {
        private NewsViewModel vm;

        public NewsPage(NewsViewModel viewModel)
        {
            InitializeComponent();
            vm = viewModel;
            this.BindingContext = vm;
        }
       
    }
}