using mobile.Model;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;

namespace mobile.ViewModel
{
    public class SermonViewModel
    {
        private ObservableCollection<Sermon> sermons;

        public ObservableCollection<Sermon> Sermons { get => sermons; set => sermons = value; }

        public SermonViewModel()
        {
            GenerateSermons();
        }

        internal void GenerateSermons()
        {
            sermons = new ObservableCollection<Sermon>();
            sermons.Add(new Sermon( "The Beauty Of Being Stuck", "02/08/2021", "https://www.youtube.com/watch?v=mLJe5yxq_7o", "Robert Madu", "http://i3.ytimg.com/vi/4G8LvFNbLYs/hqdefault.jpg" ));
            sermons.Add(new Sermon( "Trusting God in a Storm", "02/08/2021", "https://www.youtube.com/watch?v=mLJe5yxq_7o", "Tony Evans", "http://i3.ytimg.com/vi/m_9LYyXR4l8/hqdefault.jpg" ));
            sermons.Add(new Sermon( "Who is Jesus?", "02/08/2021", "https://www.youtube.com/watch?v=mLJe5yxq_7o", "Billy Graham", "http://i3.ytimg.com/vi/zjZLQY3TBd4/hqdefault.jpg" ));
            sermons.Add(new Sermon( "Your Struggle Is A Setup", "02/08/2021", "https://www.youtube.com/watch?v=mLJe5yxq_7o", "Steven Furtick", "http://i3.ytimg.com/vi/EmReBWrC17g/hqdefault.jpg" ));
            sermons.Add(new Sermon( "Reversing Discrimination Consequences", "02/08/2021", "https://www.youtube.com/watch?v=mLJe5yxq_7o", "Tony Evans", "http://i3.ytimg.com/vi/0L7TOvqXw5Q/hqdefault.jpg" ));
        }
    }
}
