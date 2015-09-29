class TakeIoClient::Message
  include Her::Model

  include_root_in_json :entry

  attributes :id, :schedule, :owner, :sender, :recipients, :contactGroups, :body, :subject, :time, 
    :wallet, :urgent, :ackUri, :replyUri, :folder, :status, :category, :type, :validity, :created, 
    :updated, :audioBase64, :async, :specificId, :idDomain, :largeAccount, :read, :location

  class << self
    def list_messages params
      all.where(params)
    end

    def get_message id
      find(id)
    end

    def send_message *args
      params = if args.size > 1
        {recipients: args[0], sender: args[1], body: args[2]}
      else
        args[0]
      end

      create(params.merge(type: 'sms'))
    end

    def send_schedulled_message *args
      params = if args.size > 2
        {recipients: args[0], sender: args[1], body: args[2], time: args[3]}
      else
        args[0].merge(time: args[1].to_s)
      end

      create(params.merge(type: 'sms'))
    end

    def update_message id, params
      save_existing(id, params)
    end
  end 
end